const { getProprietaires } = require('../../src/main/java/com/project_cloud/hallo/controller');

module.exports = async (req, res) => {
  try {
    const proprietaires = await getProprietaires();
    res.status(200).json(proprietaires);
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: 'Internal Server Error' });
  }
};