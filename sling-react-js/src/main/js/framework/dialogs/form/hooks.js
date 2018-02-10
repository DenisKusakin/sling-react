export default {

  onInit() {
      // this.each(field => field.type === 'text' &&
      //   field.set('bindings', 'TextField'));
  },

  onSuccess(form) {
    console.log('Form Values', form.values(), JSON.stringify(form.values()));
  },

  onError(form) {
    console.log('Form Errors', form.errors());
  },

  onSubmit(instance) {
    console.log('-> onSubmit HOOK -', instance.path || 'form', '- isValid?', instance.isValid, instance.values());
  },

  onClear(instance) {
    console.log('-> onClear HOOK -', instance.path || 'form');
  },

  onReset(instance) {
    console.log('-> onReset HOOK -', instance.path || 'form');
  },

  onChange(field) {
    console.log('-> onChange HOOK -', field.path, field.value);
  },

  onFocus: (field) => {
    // eslint-disable-next-line
    console.log('-> onFocus HOOK -', field.path, field.value);
  },

  onBlur: (field) => {
    console.log('-> onBlur HOOK -', field.path, field.value);
  },
};
