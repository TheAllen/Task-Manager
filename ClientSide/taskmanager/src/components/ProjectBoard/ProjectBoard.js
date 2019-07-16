import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import AddProjectTask from "./ProjectTasks/AddProjectTask";

import Backlog from "./Backlog"

class ProjectBoard extends Component {

    render() {
        //Get from params
        const {id} = this.props.match.params;
        return (
            <div className="container">
                <Link to={`/addProjectTask/${id}`} className="btn btn-primary mb-3">
                    <i className="fas fa-plus-circle"> Create Project Task</i>
                </Link>
            <br />
            <hr />

            
            <Backlog/>

            </div >
        )
    }
}

export default ProjectBoard;