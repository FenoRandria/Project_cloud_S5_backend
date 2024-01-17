const { seLogin } = require('../../src/main/java/com/project_cloud/hallo/controller');

module.exports = async (req, res) => {
  const { mail, mdp } = req.query; // Utilisation des query parameters, ajustez selon vos besoins

  try {
    const user = await seLogin(mail, mdp);
    res.status(200).json(user);
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
};