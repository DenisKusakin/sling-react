import React from 'react'
import Page from '../aem-poc/components/page';
import data from '../content/components/page1.json';
import menu from '../content/components/menu.json';

export default () => <Page pages={menu.pages} isNext data={data}/>