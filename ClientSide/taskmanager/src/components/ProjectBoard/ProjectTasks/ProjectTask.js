import React, { Component } from "react";
import { Link } from 'react-router-dom';

class ProjectTask extends Component {

    render() {
        const { projectTask } = this.props;
        let priorityString;
        let priorityClass;

        if (projectTask.priority === 1) {
            priorityClass = "bg-danger text-light";
            priorityString = "HIGH";
        } else if (projectTask.priority === 2) {
            priorityClass = "bg-warning text-light";
            priorityString = "Medium";
        }
        else if (projectTask.priority === 3) {
            priorityClass = "bg-info text-light";
            priorityString = "Low";
        }
        return (
            <div className="card mb-1 bg-light">

                <div className={`card-header text-primary ${priorityClass}`} >
                    ID: {projectTask.projectSequence} -- Priority: {priorityString}
                </div>
                <div className="card-body bg-light">
                    <h5 className="card-title">{projectTask.summary}</h5>
                    <p className="card-text text-truncate ">
                        {projectTask.ac}
                    </p>
                    <Link to={`/updateProjectTask/${projectTask.projectIdentifier}/${projectTask.projectSequence}`} className="btn btn-primary">
                        View / Update
                            </Link>

                    <button className="btn btn-danger ml-4">
                        Delete
                            </button>
                </div>
            </div>
        );
    }
}

export default ProjectTask;