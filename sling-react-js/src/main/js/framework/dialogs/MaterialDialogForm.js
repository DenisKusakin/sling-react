import React from 'react'
import {observer} from "mobx-react/index";
import Checkbox from 'material-ui/Checkbox';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import TextField from 'material-ui/TextField';
import {SketchPicker, TwitterPicker, HuePicker, GithubPicker} from 'react-color';
import reactCSS from 'reactcss'


class ColorPicker extends React.Component {
    state = {
        displayColorPicker: false
    };

    handleClick = () => {
        this.setState({displayColorPicker: !this.state.displayColorPicker})
    };

    handleClose = () => {
        this.setState({displayColorPicker: false})
    };

    render() {
        const styles = {
            width: '36px',
            height: '14px',
            borderRadius: '2px',
            background: `${this.props.color}`,
            boxShadow: '0 0 0 1px rgba(0,0,0,.1)'
        };
        const cover = {
            position: 'fixed',
            top: '0px',
            right: '0px',
            bottom: '0px',
            left: '0px',
        };

        return (
            <div style={{display: 'flex'}}>
                <div style={styles} onClick={this.handleClick}/>
                {
                    this.state.displayColorPicker && <div>
                        <div style={cover} onClick={this.handleClose}></div>
                        <SketchPicker {...this.props.colorProps} color={this.props.color}/>
                    </div>
                }
            </div>
        )
    }
}

const MaterialDialogForm = observer(({form}) => (
    <form onSubmit={form.onSubmit}>
        {
            form.map((x, i) => {
                if (x.type === 'text') {
                    return <div key={i}><TextField {...x.bind()}/><br/></div>
                }
                if (x.type === 'textarea') {
                    return <div key={i}><TextField {...x.bind()} multiLine={true}/><br/></div>
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
                            {selectProps.extra.map(x => <MenuItem value={x.value} primaryText={x.label}/>)}
                        </SelectField>
                        <br/>
                    </div>
                }
                if (x.type === 'color') {
                    const colorProps = x.bind();
                    return <div key={i}>
                        <p>{colorProps.label}</p>
                        <ColorPicker color={colorProps.value} colorProps={colorProps}/>
                        <br/>
                    </div>
                }
            })
        }
        <p>{form.error}</p>
    </form>
));

export default MaterialDialogForm;