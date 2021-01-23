import React, {useState} from 'react';
import './App.css';
import SelectTransformer from "./SelectTransformer";
import {Button, Container, Input} from "semantic-ui-react";

interface SelectHook {
    options: string[],
    setOptions: any
    handleOnSelect: (id: number) => (value: string) => void
    handleDelete: (id: number) => () => void
    addNew: () => void
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

    return {options, setOptions, handleOnSelect, handleDelete, addNew}
}



const SelectBag = (props: {hook: SelectHook}) => {
    const {hook} = props
    return <>
        {hook.options.map((opt,index) => (
            <SelectTransformer
                key={index}
                onSelect={hook.handleOnSelect(index)}
                value={opt}
                onDelete={hook.handleDelete(index)}
            />
        ))}
        <Button fluid content={"Dodaj"} onClick={() => hook.addNew()}/>
    </>
}

function App() {
    const hook = useSelectHook();



    return (
        <Container>
            <Input label={'Tekst'} fluid/>
            {JSON.stringify(hook.options)}
            <SelectBag hook={hook}/>
            <Button content={"Transformuj"}/>
        </Container>
    );
}

export default App;
