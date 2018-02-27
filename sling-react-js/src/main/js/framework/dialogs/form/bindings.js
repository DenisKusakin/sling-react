const onChange = field => (e, k, payload) => {
    field.set('value', payload);
    field.validate();
};

export default {
    TextField: {
        id: 'id',
        name: 'name',
        type: 'type',
        value: 'value',
        label: 'floatingLabelText',
        placeholder: 'hintText',
        disabled: 'disabled',
        error: 'errorText',
        onChange: 'onChange',
        onBlur: 'onBlur',
        onFocus: 'onFocus',
        autoFocus: 'autoFocus',
    },
    MaterialTextArea: {
        id: 'id',
        name: 'name',
        type: 'type',
        value: 'value',
        label: 'floatingLabelText',
        placeholder: 'hintText',
        disabled: 'disabled',
        error: 'errorText',
        onChange: 'onChange',
        onBlur: 'onBlur',
        onFocus: 'onFocus',
        autoFocus: 'autoFocus',
    },
    MaterialCheckbox: {
        id: 'id',
        name: 'name',
        type: 'type',
        value: 'checked',
        label: 'label',
        disabled: 'disabled',
        error: 'errorText',
        onChange: 'onCheck',
        onBlur: 'onBlur',
        onFocus: 'onFocus',
        autoFocus: 'autoFocus',
    },
    MaterialSelect: ({$try, field, props}) => ({
        id: $try(props.id, field.id),
        name: $try(props.name, field.name),
        type: $try(props.type, field.type),
        value: $try(props.value, field.value),
        floatingLabelText: $try(props.label, field.label),
        disabled: $try(props.disabled, field.disabled),
        errorText: $try(props.error, field.error),
        onChange: onChange(field),
        onBlur: $try(props.onBlur, field.onBlur),
        onFocus: $try(props.onFocus, field.onFocus),
        autoFocus: $try(props.autoFocus, field.autoFocus),
        extra: $try(props.extra, field.extra)
    }),
    ColorPicker: ({$try, field, props}) => ({
        id: $try(props.id, field.id),
        name: $try(props.name, field.name),
        type: $try(props.type, field.type),
        value: $try(props.value, field.value),
        color: $try(props.value, field.value),
        label: $try(props.label, field.label),
        onChange: val => field.set('value', val.hex)
    }),
    AceCodeEditor: ({$try, field, props}) => ({
        id: $try(props.id, field.id),
        name: $try(props.name, field.name),
        type: $try(props.type, field.type),
        value: $try(props.value, field.value),
        color: $try(props.value, field.value),
        label: $try(props.label, field.label),
        onChange: val => field.set('value', val)
    })
}