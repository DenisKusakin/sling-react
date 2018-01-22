import Title from './title'
import Subtitle from './subtitle'
import Container from './container/dialog'
import standartDialog from '../framework/dialogs/standartDialog'

export default {
    Title: standartDialog(Title),
    Subtitle: standartDialog(Subtitle),
    Container,
    UpdatableContainer: Container
}