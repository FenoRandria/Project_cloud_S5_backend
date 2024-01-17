const { inscrire } = require('../../src/main/java/com/project_cloud/hallo/controller');
module.exports = async (req, res) => {
    const { nom, mail, mdp, cmdp, dtn } = req.query; // Utilisation des query parameters, ajustez selon vos besoins
  
    try {
      // Conversion de la date si nécessaire
      const date = new Date(dtn);
  
      const result = await inscrire({ nom, mail, mdp, date }, cmdp);
      res.status(200).json(result);
    } catch (error) {
      console.error(error);
      res.status(500).json({ error: 'Internal Server Error' });
    }
  };