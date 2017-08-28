import React from 'react'
import DropDownMenu from 'material-ui/DropDownMenu';
import MenuItem from 'material-ui/MenuItem';

let handleChangeWrapper = f => (event, index, value) => f(value)

const MockPages = ({items, currentPage, handleChange}) => {
    return <div>
        <DropDownMenu value={currentPage} onChange={handleChangeWrapper(handleChange)}>
            {items.map( ({title, id}) => (<MenuItem key={id} value={id} primaryText={title} />))}
        </DropDownMenu>
    </div>
}

export default MockPages