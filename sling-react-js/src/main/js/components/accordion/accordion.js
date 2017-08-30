import React from 'react';

import {
    Accordion,
    AccordionItem,
    AccordionItemTitle,
    AccordionItemBody,
} from 'react-accessible-accordion';

import 'react-accessible-accordion/dist/react-accessible-accordion.css';

const SlingAccordion = ({items}) => (
    <Accordion>
        {
            items.map( ({title, body}) => {
                return(
                    <AccordionItem>
                        <AccordionItemTitle>{title}</AccordionItemTitle>
                        <AccordionItemBody>
                            {body}
                        </AccordionItemBody>
                    </AccordionItem>
                )
            })
        }
    </Accordion>
);

export default SlingAccordion;
