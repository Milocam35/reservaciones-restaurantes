import LoginPage from './pages/LoginPage'
import SignUpPage from './pages/SignUpPage';
import RestaurantsPage from './pages/RestaurantsPage';
import { UserProvider } from './store/UserProvider';
import UserPage from './pages/UserPage'
import './styles/App.css'
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import ReservationPage from './pages/ReservationPage';

function App() {
  return (
    <UserProvider>
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/signUp" element={<SignUpPage />} />
        <Route path="/Restaurants" element={<RestaurantsPage/>} />
        <Route path="/Restaurants/Reservations" element={<ReservationPage/>} />
        <Route path="/Restaurants/User" element={<UserPage/>}/>
      </Routes>
    </Router>
    </UserProvider>
  )
}

export default App
