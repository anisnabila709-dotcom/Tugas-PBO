LoginController lc = new LoginController();
User user = lc.login(email, password);

if (user != null) {
    // simpan ke Session
    Session.setUser(user);
    new DashboardView();
} else {
    JOptionPane.showMessageDialog(this, "Email atau password salah!");
}
