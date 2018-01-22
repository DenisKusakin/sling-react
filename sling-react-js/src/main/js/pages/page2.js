import React from 'react'
import PageContent from './../content/page2.json'
import AuthorApp from '../framework/AuthorApp'
import components from './../components'
import dialogs from './../components/dialog'

export default () => <AuthorApp content={PageContent} components={components} dialogs={dialogs}/>