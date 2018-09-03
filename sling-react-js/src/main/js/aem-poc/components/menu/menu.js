import React from 'react'

export default ({ links }) => {
    return (<div className="preview-menu">
        <div className="preview-menu-h">Pages</div>
            {
                links.map(({ link, title }) =>
                    <div className="preview-menu-item">
                        <a href={ link }>{ title }</a>
                    </div>
                )
            }
    </div>);
}
