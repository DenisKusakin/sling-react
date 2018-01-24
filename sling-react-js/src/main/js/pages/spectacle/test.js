import React from 'react'
import Page1 from './../../content/spectacle/presentation.json'
import components from './../../spectacle'
import AuthorApp from './../../framework/AuthorApp'
import PublisherApp from './../../framework/PublisherApp'

export default () => <PublisherApp content={Page1} components={components} dialogs={components}/>