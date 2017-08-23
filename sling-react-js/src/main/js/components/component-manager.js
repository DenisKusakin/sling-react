import Root from './page.js'
import Greeting from './title-component.js'
import Slider from './slider-component.js'
import Container from './container.js'
import Application from './application.js'
import ImageGallery from './image-gallery.js'
import Accordion from './accordion.js'
import SelfUpdatableContainer from './self-updatable-container.js'
//TODO: fix
import editableComponent from './../authoring-components/hoc/editable-component.js'

export default {
    Root,
    Application,
    Greeting: editableComponent(Greeting),
    Slider,
    Container,
    ImageGallery: editableComponent(ImageGallery),
    Accordion,
    SelfUpdatableContainer
}