import React from 'react'
import {observer} from "mobx-react/index";
import Checkbox from 'material-ui/Checkbox';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import TextField from 'material-ui/TextField';

const MaterialDialogForm = observer(({form}) => (
    <form onSubmit={form.onSubmit}>
        {
            form.map((x, i) => {
                if (x.type === 'text') {
                    return <div key={i}><TextField {...x.bind()}/><br/></div>
                }
                if (x.type === 'checkbox') {
                    return <div key={i}>
                        <Checkbox {...x.bind()}/>
                        <br/>
                    </div>
                }
                if (x.type === 'select') {
                    const selectProps = x.bind();
                    return <div key={i}>
                        <SelectField {...selectProps}>
                            {selectProps.extra.map(x => <MenuItem value={x.value} primaryText={x.label} />)}
                        </SelectField>
                        <br/>
                    </div>
                }
            })
        }
        <p>{form.error}</p>
    </form>
));

export default MaterialDialogForm;