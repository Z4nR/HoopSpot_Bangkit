const router = require('express').Router();
const { capstone } = require('../controllers');

/* GET localhost:8080/capstone => Ambil semua data */
router.get('/', capstone.getAll);

/* GET localhost:8080/capstone => Ambil data hoops_entity */
router.get('/hoops_entity', capstone.getHoopsEntity);
/* GET localhost:8080/capstone => Ambil data parking */
router.get('/parking', capstone.getParking);

/* GET localhost:8080/capstone/? => Ambil data hoops_entity berdasarkan place_id = ? */
router.get('/hoops_entity/:place_id', capstone.getHoopsEntityID);
/* GET localhost:8080/capstone/? => Ambil data parking berdasarkan park_id = ? */
router.get('/parking/:park_id', capstone.getParkingID);

/* POST localhost:8080/capstone/add => Tambah data hoops_entity */
router.post('/hoops_entity/add', capstone.addHoopsEntity);
/* POST localhost:8080/capstone/add => Tambah data parking */
router.post('/parking/add', capstone.addParking);

/* POST localhost:8080/capstone/edit/? => Edit data hoops_entity */
router.post('/hoops_entity/edit/:id', capstone.editHoopsEntity);
/* POST localhost:8080/capstone/edit/? => Edit data parking */
router.post('/parking/edit/:id', capstone.editParking);

/* POST localhost:8080/capstone/delete/? => Delete data hoops_entity */
router.post('/hoops_entity/delete/:id', capstone.deleteHoopsEntityID);
/* POST localhost:8080/capstone/delete/? => Delete data parking */
router.post('/parking/delete/:id', capstone.deleteParkingID);

/* POST localhost:8080/capstone/delete => Delete all data --Failed--
router.post('/delete', capstone.deleteAll); */

module.exports = router;