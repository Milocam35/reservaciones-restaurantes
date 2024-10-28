import LoginPage from './pages/LoginPage'
import SignUpPage from './pages/SignUpPage';
import RestaurantsPage from './pages/RestaurantsPage';
import { UserProvider } from './store/UserProvider';
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
        <Route path="/restaurantsPage" element={<RestaurantsPage/>} />
        <Route path="/restaurantsPage/reservationPage" element={<ReservationPage/>} />
      </Routes>
    </Router>
    </UserProvider>
  )
}

export default App
