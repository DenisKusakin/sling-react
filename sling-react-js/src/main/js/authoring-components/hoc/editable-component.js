import React from 'react';
import { ContextMenuProvider } from 'react-contexify';
import EditIcon from 'react-material-icons/icons/editor/mode-edit';
import IconButton from 'material-ui/IconButton'
import { MuiThemeProvider } from 'material-ui/styles';
import darkTheme from 'material-ui/styles/baseThemes/darkBaseTheme';
import './border.css';

const styles = {
    smallIcon: {
      width: 15,
      height: 15,
    }
  };

const editableComponent = Component => class EditableComponent extends React.Component {
    constructor(props){
        super(props)
    }

    render(){
        let {__propTypes, ...props} = this.props
        
        return <div className="edit-wrapper">
            <div style={{float: 'left'}}>
                <MuiThemeProvider theme={darkTheme}>
                    <IconButton onClick={() => console.log(__propTypes)} iconStyle={styles.smallIcon}>
                        <EditIcon/>
                    </IconButton>
                </MuiThemeProvider>
            </div>
            <Component {...props} isEditMode={true}/>
        </div>

    }
}

export default editableComponent