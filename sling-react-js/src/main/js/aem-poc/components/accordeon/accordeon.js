import { UnmountClosed } from 'react-collapse';
import React from 'react';
import './accordeon.css';

class Accordeon extends React.Component {
    constructor(props) {
        super(props);
        this.state = { isOpen: !!props.isOpen };
        this.onClick = this.onClick.bind(this);
    }

    onClick() {
        this.setState({ isOpen: !this.state.isOpen });
    }

    render() {
        const { headline, header, children } = this.props;
        const isOpen = this.state.isOpen;
        return (
            <div className="ds-accordion">
                <h2 className="ds-accordion-headline">{headline}</h2>
                <div className={`ds-accordion-header${isOpen ? '' : ' collapsed'}`} onClick={this.onClick}>{ header }</div>
                <UnmountClosed className="ds-accordion-body" hasNestedCollapse springConfig={{ stiffness: 300, damping: 28 }} isOpened={isOpen}>
                    <div className="ds-accordion-content">
                        { children }
                    </div>
                </UnmountClosed>
            </div>
        );
    }
}

export default Accordeon;
