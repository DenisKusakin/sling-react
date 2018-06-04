import React from 'react';

export const AuthorContext = React.createContext({
    isEditMode: true,
    toggleEdit: () => {
    },
    updateFromServer: () => {
    }
});