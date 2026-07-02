import { Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import BuyerDashboard from "./pages/BuyerDashboard";
import SellerDashboard from "./pages/SellerDashboard";
import Properties from "./pages/Properties";
import BuyerProperties from "./pages/BuyerProperties";
import BuyerMyProperties from "./pages/BuyerMyProperties";
import BuyerRequests from "./pages/BuyerRequests";
import SellerRequests from "./pages/SellerRequests";
import SellerMaintenance from "./pages/SellerMaintenance";
import SellerPayments from "./pages/SellerPayments";
import BuyerPayments from "./pages/BuyerPayments";
import SellerProfile from "./pages/SellerProfile";
import BuyerProfile from "./pages/BuyerProfile";

function App() {

  return (

      <Routes>

          <Route
              path="/"
              element={<Login />}
          />

          {/* Buyer Routes */}

          <Route
              path="/buyer"
              element={<BuyerDashboard />}
          />

          <Route
              path="/buyer/properties"
              element={<BuyerProperties />}
          />

          <Route
              path="/buyer/my-properties"
              element={<BuyerMyProperties />}
          />

          <Route
              path="/buyer/requests"
              element={<BuyerRequests />}
          />

          <Route
              path="/buyer/payments"
              element={<BuyerPayments />}
          />

          <Route
              path="/buyer/profile"
              element={<BuyerProfile />}
          />

          {/* Seller Routes */}

          <Route
              path="/seller"
              element={<SellerDashboard />}
          />

          <Route
              path="/seller/properties"
              element={<Properties />}
          />

          <Route
              path="/seller/requests"
              element={<SellerRequests />}
          />

          <Route
              path="/seller/maintenance"
              element={<SellerMaintenance />}
          />

          <Route
              path="/seller/payments"
              element={<SellerPayments />}
          />

          <Route
              path="/seller/profile"
              element={<SellerProfile />}
          />

      </Routes>

  );
}

export default App;