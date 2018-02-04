import React from 'react'
import dynamic from 'next/dynamic'
import SpectacleAuthorRoot from './SpectaceAuthorRoot'
import standartDialog from "../framework/dialogs/standartDialog";
import SimpleDialog from '../framework/dialogs/SimpleDialog'
import ContainerDialog from './../components/container/dialog';
//TODO: refactoring is required
const wrapStandartDialog = components => {
    const res = {
        components: {},
        dialogs: {}
    };
    Object.keys(components).forEach(value => {
        if (value.startsWith('__WRAP_')) {
            const Comp = components[value];
            res.dialogs[value.substr('__WRAP_'.length)] = props => <SimpleDialog
                {...props}
                renderComp={
                    x => <Comp {...x}/>
                }
            />
            // res.dialogs[value.substr('__WRAP_'.length)] = standartDialog(components[value])
            //standartDialog(components[value])
        } else if (value.startsWith('__')) {
            res.dialogs[value.substr(2)] = components[value];
        } else {
            res.components[value] = components[value];
        }
    });
    return res;
};

const Spectacle = dynamic({
    modules: props => {
        return {
            Deck: import('./deck'),
            Heading: import('./heading'),
            Text: import('./text'),
            Slide: import('./slide'),
            Container: import('./../components/container')
            // '__Deck': import('./deck'),
            // '__WRAP_Heading': import('./heading'),
            // '__WRAP_Text': import('./text'),
            // '__Slide': import('./slide'),
            // '__Container': import('./../components/container/dialog')
        }

    },
    ssr: false,
    render: (props, components) => {
        // const {components, dialogs} = wrapStandartDialog(componentsAndDialogs)
        return (
            <SpectacleAuthorRoot
                content={props.content}
                components={{
                    ...components,
                    "dialogs/SimpleDialog": SimpleDialog,
                    "dialogs/Container": ContainerDialog
                }}
            />
        )
    }
});

export default Spectacle