// import validatorjs from 'validatorjs';

import MobxReactForm from 'mobx-react-form';
import hooks from './hooks';
import bindings from './bindings';
import validatorjs from 'validatorjs';

export default class Form extends MobxReactForm {

    bindings() {
        return bindings;
    }

    hooks() {
        return hooks;
    }

    plugins() {
        return {
            dvr: validatorjs,
        };
    }
}
