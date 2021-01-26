import {Button, Dropdown, Form, Icon, Select} from "semantic-ui-react";
import { $enum } from "ts-enum-util";
import {DropTargetMonitor, useDrag, useDrop, XYCoord} from "react-dnd";
import React, {useRef} from "react";

enum TransformerType {
    acronym,
capitalize_inversion,
capitalize,
latex,
lower,
number_to_text,
repetition_elimination,
text_to_acronym,
upper
}

const translateName = {
    acronym: "Rozszerz skróty",
    capitalize_inversion: "Odwróć tekst z zachowaniem wielkości liter",
    capitalize: "Wyrazy z wielkich liter",
    latex: "Format Latex",
    lower: "Zmień na małe litery",
    number_to_text: "Transformuj liczby na słowa",
    repetition_elimination: "Usuń powtórzenia",
    text_to_acronym: "Skróć wyrazy",
    upper: "Zamień wszystkie słowa na wielkie litery"
}

const options = $enum(TransformerType).map((value, key) => ({ key: key, value: key, text: translateName[key] }))

interface Props {
    id: any
    onSelect: (data: string) => void
    onDelete: () => void
    value: string,
    index: number,
    moveCard: (dragIndex: number, hoverIndex: number) => void
}


const style: React.CSSProperties = {
    border: '1px dashed gray',
    padding: '0.5rem 1rem',
    marginBottom: '.5rem',
    backgroundColor: 'white',
    width: '20rem',
}

const handleStyle: React.CSSProperties = {
    backgroundColor: 'green',
    width: '1rem',
    height: '1rem',
    display: 'inline-block',
    marginRight: '0.75rem',
    cursor: 'move',
}

interface DragItem {
    index: number
    id: string
    type: string
}

export default (props: Props) => {
    const ref = useRef<HTMLDivElement>(null)
    const [, drop] = useDrop({
        accept: "option",
        hover(item: DragItem, monitor: DropTargetMonitor) {
            if (!ref.current) {
                return
            }
            const dragIndex = item.index
            const hoverIndex = props.index

            // Don't replace items with themselves
            if (dragIndex === hoverIndex) {
                return
            }

            // Determine rectangle on screen
            const hoverBoundingRect = ref.current?.getBoundingClientRect()

            // Get vertical middle
            const hoverMiddleY =
                (hoverBoundingRect.bottom - hoverBoundingRect.top) / 2

            // Determine mouse position
            const clientOffset = monitor.getClientOffset()

            // Get pixels to the top
            const hoverClientY = (clientOffset as XYCoord).y - hoverBoundingRect.top

            // Only perform the move when the mouse has crossed half of the items height
            // When dragging downwards, only move when the cursor is below 50%
            // When dragging upwards, only move when the cursor is above 50%

            // Dragging downwards
            if (dragIndex < hoverIndex && hoverClientY < hoverMiddleY) {
                return
            }

            // Dragging upwards
            if (dragIndex > hoverIndex && hoverClientY > hoverMiddleY) {
                return
            }

            // Time to actually perform the action
            props.moveCard(dragIndex, hoverIndex)

            // Note: we're mutating the monitor item here!
            // Generally it's better to avoid mutations,
            // but it's good here for the sake of performance
            // to avoid expensive index searches.
            item.index = hoverIndex
        },
    })

    const [{ isDragging }, drag, preview] = useDrag({
        item: { type: "option", id: props.id, index: props.index },
        collect: (monitor: any) => ({
            isDragging: monitor.isDragging(),
        }),
    })

    const opacity = isDragging ? 0 : 1
    drag(drop(ref))

    // const [{ opacity }, drag, preview] = useDrag({
    //     item: { type: "option" },
    //     collect: (monitor) => ({
    //         opacity: monitor.isDragging() ? 0.4 : 1,
    //     }),
    // })

    return  <div ref={preview} style={{display: 'flex', margin: "2em 0"}}>
            <div ref={ref}>
                <Button icon>
                    <Icon name={'arrows alternate vertical'}/>
                </Button>
            </div>
            <Dropdown
                placeholder='Wybierz transformator'
                fluid
                search
                selection
                options={options}
                onChange={(event, data) => props.onSelect(data.value as string)}
                value={props.value}
            />
            <Button icon onClick={props.onDelete}>
                <Icon name={'delete'}/>
            </Button>
        </div>
    }