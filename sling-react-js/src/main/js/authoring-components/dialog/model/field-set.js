import { observable, computed, action } from "mobx";
import Field from "./field.js"

class FieldSet {
    @observable fields

    constructor(fields = []){
        this.fields = fields
            .map(({persistenceProps, value}) => new Field({
                persistenceProps,
                initialValue: value
            }))
    }

    @computed get values() {
        return this.fields
            .map(field => ({
                persistenceProps: field.persistenceProps,
                value: field.value
            }))
    }
}

export default FieldSet