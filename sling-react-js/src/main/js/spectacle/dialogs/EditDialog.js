import React from 'react'
import EditIcon from 'material-ui/svg-icons/editor/mode-edit';
import DeleteIcon from 'material-ui/svg-icons/action/delete';
import IconButton from 'material-ui/IconButton'
import {deleteResource} from './../../framework/api';
import EditForm from './EditForm'

const styles = {
    smallIcon: {
        width: 12,
        height: 12,
    }
};

class EditDialog extends React.Component {

    render() {
        return <EditForm {...this.props}>
            {
                ({isEditMode, showDialog, updateFromServer}) => {
                    if (isEditMode) {
                        return <div>
                            <style jsx>
                                {`
                                    .container{
                                        display: flex;
                                        align-items: center;
                                    }
                                `}
                            </style>
                            <div className="container">
                                <div style={{flexGrow: 0, zIndex: 1}}>
                                    <div style={{display: 'flex', flexDirection: 'column'}}>
                                        {
                                            this.props.editUrl &&
                                            <IconButton iconStyle={styles.smallIcon} onClick={showDialog}>
                                                <EditIcon/>
                                            </IconButton>
                                        }
                                        {this.props.deleteUrl &&
                                        <IconButton iconStyle={styles.smallIcon} onClick={() => {
                                            if (!this.props.deleteUrl) {
                                                console.warn("Delete URL is not defined")
                                            } else {
                                                deleteResource({url: this.props.deleteUrl})
                                                    .then(() => {
                                                        console.info("Resource successfully removed", this.props.deleteUrl);
                                                        updateFromServer()
                                                    })
                                                    .catch(() => {
                                                        console.error(`Remove resource failed, url: ${this.props.deleteUrl}`)
                                                    })
                                            }
                                        }}>
                                            <DeleteIcon/>
                                        </IconButton>}
                                    </div>
                                </div>
                                <div style={{flexGrow: 1}}>
                                    {this.props.children}
                                </div>
                            </div>
                        </div>
                    }
                    return this.props.children
                }
            }
        </EditForm>
    }
}

export default EditDialog;