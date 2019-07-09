import React, { Component } from 'react';
import { Link } from 'react-router-dom';

//Functional component
const CreateTaskButton = () => {
    return (
        <React.Fragment>
            <Link to="/addTask" className="btn btn-lg btn-info display-5">
                Create a Task
            </Link>
        </React.Fragment>
    );
}

export default CreateTaskButton;