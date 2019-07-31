import React, { Component } from "react";
import ProjectTask from "./ProjectTasks/ProjectTask";

class Backlog extends Component {

    render() {
        const {projectTasksProp} = this.props;
        const tasks = projectTasksProp.map(projectTask => (
            <ProjectTask key={projectTask.id} projectTask={projectTask} />
        ));

        let todoItems = [];
        let inProgressItems = [];
        let completedItems = [];

        for(let i = 0; i < tasks.length; i++){
            if(tasks[i].props.projectTask.status === "TO_DO"){
                todoItems.push(tasks[i]);
            }
            else if(tasks[i].props.projectTask.status === "IN_PROGRESS"){
                inProgressItems.push(tasks[i]);
            }
            else if(tasks[i].props.projectTask.status === "DONE"){
                completedItems.push(tasks[i]);
            }
        }

        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-4">
                        <div className="card text-center mb-2">
                            <div className="card-header bg-secondary text-white">
                                <h3>TO DO</h3>
                            </div>
                        </div>

                        {todoItems}
                        {

                        }
                        
                    </div>
                    <div className="col-md-4">
                        <div className="card text-center mb-2">
                            <div className="card-header bg-primary text-white">
                                <h3>In Progress</h3>
                            </div>
                        </div>

                        {/* <ProjectTask/> */}
                        {inProgressItems}
                        {

                        }

                    </div>
                    <div className="col-md-4">
                        <div className="card text-center mb-2">
                            <div className="card-header bg-success text-white">
                                <h3>Done</h3>
                            </div>
                        </div>

                        {/* <ProjectTask/> */}
                        {completedItems}
                        {

                        }


                    </div>
                </div>
            </div>
        )
    }
}

export default Backlog;