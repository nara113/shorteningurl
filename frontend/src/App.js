import React from 'react';
import {BrowserRouter, Route} from 'react-router-dom';
import Main from "./components/main";

const App = () =>(
    <BrowserRouter>
        <div>
            <Route exact path = "/" component={Main}/>
        </div>
    </BrowserRouter>
)

export default App;