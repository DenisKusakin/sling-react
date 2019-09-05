import React from 'react'

const Title = ({ title }) => <h1>{title}</h1>;

const mapResourceToProps = ({title}) => ({title})
const ResourceTitle = connect(mapResourceToProps)(Title)

const ResourceTitle = () => <Resource>
    {
        resProps => <Title title={resProps.title}/>
    }
</Resource>

const Container = ({children}) => <div>{children}</div>

export default Title;