import React, { Component } from 'react';
import ProjectItem from './Project/ProjectItems'
import Header from './Layout/Header'
import CreateTaskButton from './Project/CreateTaskButton'
import { connect } from 'react-redux'
import { getProjects } from '../actions/projectAction'
import PropTypes from "prop-types"

class DashBoard extends Component {

    componentDidMount() {
        this.props.getProjects();
    }

    render() {

        const {projects} = this.props.project;

        // const projectObject = {
        //     projectName: "ProjectName PROPS",
        //     projectIdentifier: "PROP",
        //     description: "description from props"
        // };

        return (
            <div>
                <div className="projects">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-12">
                                <h1 className="display-4 text-center">Tasks and Todos</h1>
                                <br />
                                <CreateTaskButton />
                                <br />
                                <hr />
                                {
                                    projects.map(project=>(
                                        <ProjectItem key={project.id} project={project}/>
                                    ))
                                }
                            </div>
                        </div>
                    </div>
                </div>

            </div>



        );
    }
}

DashBoard.propTypes = {
    project: PropTypes.object.isRequired,
    getProjects: PropTypes.func.isRequired
}

const mapStateToProps = state => ({
    project: state.project,
});

export default connect(mapStateToProps, { getProjects })(DashBoard);