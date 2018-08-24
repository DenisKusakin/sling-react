import Cookie from 'js-cookie';

export default {
    upadeIframe: () => {
        if (window && window.parent) {
            const elem = window.parent.document.querySelector('iframe#ContentFrame');
            if (elem) {
                elem.dispatchEvent(new Event('load'));
            }
        }
    },
    isEditMode: () => Cookie.get('cq-editor-layer.page') === 'Edit',
    onLayerToggle: (onPreviewOpen, onEditOpen) => {
        if (window && window.parent) {
            window.parent.$(window.parent.document).on('cq-layer-activated', (e) => {
                if (e.prevLayer === 'Edit' && e.layer === 'Preview') {
                    onPreviewOpen();
                } else if (e.prevLayer === 'Preview' && e.layer === 'Edit') {
                    onEditOpen();
                }
            });
        }
    }
}