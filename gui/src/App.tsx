import React, {useCallback, useEffect, useState} from 'react';
import './App.css';
import SelectTransformer from "./SelectTransformer";
import {Button, Container, Divider, Input, Message} from "semantic-ui-react";
import useFetch from 'use-http'
import { useDrag } from 'react-dnd'
import update from 'immutability-helper'

interface SelectHook {
    options: string[],
    setOptions: any
    handleOnSelect: (id: number) => (value: string) => void
    handleDelete: (id: number) => () => void
    addNew: () => void
    moveCard: (dragIndex: number, hoverIndex: number) => void
}

const useSelectHook = () : SelectHook => {
    const [options, setOptions] = useState<string[]>([""]);
    const handleOnSelect = (id: number) => (value: string) => {
        setOptions(prevState => {
            return [...prevState.slice(0, id), value, ...prevState.slice(id+1)]
        })
    }

    const handleDelete = (id: number) => () => {
        setOptions(prevState => [...prevState.slice(0, id), ...prevState.slice(id+1)])
    }

    const addNew = () => {
        setOptions(prevState => [...prevState, ""])
    }

    const moveCard = useCallback(
        (dragIndex: number, hoverIndex: number) => {
            const dragCard = options[dragIndex]
            setOptions(
                update(options, {
                    $splice: [
                        [dragIndex, 1],
                        [hoverIndex, 0, dragCard],
                    ],
                }),
            )
        },
        [options],
    )

    return {options, setOptions, handleOnSelect, handleDelete, addNew, moveCard}
}



const SelectBag = (props: {hook: SelectHook}) => {
    const {hook} = props

    return <>
        {hook.options.map((opt,index) => (
            <SelectTransformer
                moveCard={hook.moveCard}
                index={index}
                id={opt}
                onSelect={hook.handleOnSelect(index)}
                value={opt}
                onDelete={hook.handleDelete(index)}
            />
        ))}
        <Button fluid content={"Dodaj transformator"} onClick={() => hook.addNew()}/>
    </>
}

function App() {
    const hook = useSelectHook();
    const [text, setText] = useState("")
    const {post, response} = useFetch("http://localhost:8080/transform")

    useEffect(() => {
        post({transforms: hook.options.filter(x => x), text})
    }, [text, hook.options])

    return (
        <Container>
            <Divider hidden/>
            <Input label={'Tekst'} fluid value={text} onChange={(event, data) => setText(data.value)}/>
            <SelectBag hook={hook}/>
            <Divider />
            {response.data?.text && <Message>{response.data.text}</Message>}
        </Container>
    );
}

export default App;
