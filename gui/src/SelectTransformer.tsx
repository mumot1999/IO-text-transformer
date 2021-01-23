import {Button, Dropdown, Form, Icon, Select} from "semantic-ui-react";
import { $enum } from "ts-enum-util";

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

const options = $enum(TransformerType).map((value, key) => ({ key: key, value: key, text: key }))

interface Props {
    onSelect: (data: string) => void
    onDelete: () => void
    value: string
}

export default (props: Props) => {
    return   <>
        <Dropdown
            placeholder='Select Country'
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
        </>
}