import { observable, computed, action } from "mobx";

class Field {
    @observable value = null
    name = null

    constructor({initialValue, name}){
        this.name = name
        this.value = initialValue
    }
}

export default Field