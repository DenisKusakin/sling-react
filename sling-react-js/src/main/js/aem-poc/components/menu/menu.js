import React from 'react'
import Link from 'next/link';

export default ({ pages, isNext }) => {
    const A = (isNext ? Link : 'a');
    return (<div className="preview-menu">
        <div className="preview-menu-h">Pages</div>
            {
                pages.map(({ link, title }) =>
                    <div className="preview-menu-item">
                        <A href={ link }><a>{ title }</a></A>
                    </div>
                )
            }
    </div>);
}
