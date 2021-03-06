import React, { Component } from 'react';
import { Route, Redirect } from 'react-router-dom';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';

const SecureRoute = ({ component: Component, security, ...otherProps }) => (
    <Route {...otherProps}
        render={props => security.validToken === true ?
            (<Component {...props} />) : (<Redirect to="login" />
            )
        }
    />
);

SecureRoute.propTypes = {
    security: PropTypes.object.isRequired
};

const mapStatesToProps = state => ({
    security: state.security
});

export default connect(mapStatesToProps)(SecureRoute);
