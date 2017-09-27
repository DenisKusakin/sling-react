import { observable, computed, action } from "mobx";

class Field {
    @observable value = null
    persistenceProps = null

    constructor({initialValue, persistenceProps}){
        this.persistenceProps = persistenceProps
        this.value = initialValue
    }
}

export default Field