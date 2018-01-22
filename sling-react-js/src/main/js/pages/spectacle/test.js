import React from 'react'
import Page1 from './../../content/spectacle/presentation.json'
import components from './../../spectacle'
import AuthorApp from './../../framework/AuthorApp'

export default () => <AuthorApp content={Page1} components={components} dialogs={components}/>