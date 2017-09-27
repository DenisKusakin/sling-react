import React from 'react';
//import './border.css';

class ErrorBoundary extends React.Component {
    constructor(props) {
      super(props);
      this.state = { hasError: false };
    }
  
    componentDidCatch(error, info) {
      // Display fallback UI
      this.setState({ hasError: true });
    }
  
    render() {
      if (this.state.hasError) {
        // You can render any custom fallback UI
        return <h1>Something went wrong.</h1>;
      }
      return this.props.children;
    }
  }

const editableComponent = (Component, Dialog) => (props) => {
    if (Dialog && props.__propTypes) {
        return <ErrorBoundary>
            <Dialog {...props} />
        </ErrorBoundary>
    } else {
        return <ErrorBoundary>
            <Component {...props} />
        </ErrorBoundary>
    }
}

{/* <div className="edit-wrapper">
    <div style={{ float: 'left' }}>
        {Dialog && <Dialog />}
    </div>
    <Component {...props} />
</div> */}

export default editableComponent