import React, { useCallback } from 'react';
import { createUser } from './headerservices/signupService';

function HeaderComponent(){

  const signUp = useCallback(() => {

    let user = document.getElementById("signupusername").value;
    let pass = document.getElementById("signuppassword").value;
    let pass2 = document.getElementById("confirmPassword").value;


    if(pass === pass2){
      let pobj = createUser(user, pass);

      pobj.then((response) => {
        console.log(JSON.stringify(response.data, null, 3));
      });

      pobj.catch((error) => {
        console.log(JSON.stringify(error, null, 3))
      });

      document.getElementById("suSuc").style.display = "block";
      document.getElementById("suFail").style.display = "none";
    }
    else{
      console.error("Passwords didn't match");

      document.getElementById("suFail").style.display = "block";
      document.getElementById("suSuc").style.display = "none";
    }
  
  }, []);

  const signIn = useCallback(() => {}, []);

  return (
      <div  className="container-fluid">
        <div className="row">
          <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="col-10">
              <a className="navbar-brand" href="/dashboard">Legacy Library</a>
            </div>
            <div className="col">
              <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
              </button>
          
              <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                  <li className="nav-item">
                    <button type="button" className="btn nav-link" data-bs-toggle="modal" data-bs-target="#signUpModal">Sign up</button>
                  </li>
                  <li className="nav-item">
                    <button type="button" className="btn nav-link" data-bs-toggle="modal" data-bs-target="#signInModal">Sign In</button>
                  </li>
                </ul>
              </div>
              <div className="modal fade" id="signUpModal" data-bs-backdrop="static" data-bs-keyboard="false" tabIndex="-1" aria-labelledby="signUpModalLabel" aria-hidden="true">
                <div className="modal-dialog">
                  <div className="modal-content">
                    <div className="modal-header">
                      <h5 className="modal-title" id="signUpModalLabel">Sign Up</h5>
                      <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div className="modal-body">
                      <form autoComplete="on">
                          <div>
                            <label id="signupusernameLabel" htmlFor="signupusername">Username</label>
                            <br/>
                            <input type="text" id="signupusername"/>
                          </div>
                          <div>
                            <label id="signuppasswordLabel" htmlFor="signuppassword">Password</label>
                            <br/>
                            <input type="password" id="signuppassword"></input>
                          </div>
                          <div>
                            <label id="confirmPasswordLabel" htmlFor="confirmPassword">Confirm Password</label>
                            <br/>
                            <input type="password" id="confirmPassword"></input>
                          </div>
                      </form>
                    </div>
                    <div className="modal-footer">
                      <p id="suSuc" style={{'display': 'none'}}>Sign Up Successful</p>
                      <p id="suFail" style={{'display': 'none'}}>Passwords do not match!</p>
                      <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                      <button onClick={signUp} type="button" className="btn btn-primary">Save</button>
                    </div>
                  </div>
                </div>
              </div>
              <div className="modal fade" id="signInModal" data-bs-backdrop="static" data-bs-keyboard="false" tabIndex="-1" aria-labelledby="signInModalLabel" aria-hidden="true">
                <div className="modal-dialog">
                  <div className="modal-content">
                    <div className="modal-header">
                      <h5 className="modal-title" id="signInModalLabel">Sign In</h5>
                    </div>
                    <div className="modal-body">
                      <form autoComplete="on">
                        <div>
                          <label id="signinusernameLabel" htmlFor="signinusername=">Username</label>
                          <br/>
                          <input type="text" id="signinusername"/>
                        </div>
                        <div>
                          <label id="signinpasswordLabel" htmlFor="signinpassword">Password</label>
                          <br/>
                          <input type="password" id="signinpassword"></input>
                        </div>
                      </form>
                    </div>
                    <div className="modal-footer">
                      <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                      <button onClick={signIn} type="button" className="btn btn-primary">Log In</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </nav>
        </div>
      </div>
  );
}

export default HeaderComponent;