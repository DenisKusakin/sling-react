import React from 'react'
// import Title from './title.js'
// import SimpleDialog from './../../authoring-components/dialog/widgets/simple-dialog.jsx'
import Lightbox from 'react-images';

class ImageGalleryDialog extends React.Component {
    // constructor(props){
    //     super(props)
    //     let propTypes = props.__propTypes
    //     this.fields = propTypes.map( ({value, persistenceProps: {name}}) => ({name, value}) )
    // }

    render() {
        let length = this.props.__propTypes.value ? this.props.__propTypes.value.length : 1
        let size = Math.round(100/length)
        return <div>
            {this.props.__propTypes.value && 
                this.props.__propTypes.value.map( ({src}) => <img src={src} style={{ width: `${size}%`, height: `${size}%`}}/>)
            }
        </div>
    }
}

export default ImageGalleryDialog
