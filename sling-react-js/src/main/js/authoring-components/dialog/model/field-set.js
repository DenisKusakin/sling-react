import { observable, computed, action } from "mobx";
import Field from "./field.js"

class FieldSet {
    @observable fields

    constructor(fields = []){
        this.fields = fields
            .map(({name, value}) => new Field({
                name,
                initialValue: value
            }))
    }

    @computed get values() {
        return this.fields
            .map(field => ({
                name: field.name,
                value: field.value
            }))
    }
}

export default FieldSet