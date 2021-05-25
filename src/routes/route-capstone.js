const router = require('express').Router();
const { capstone } = require('../controllers');

// GET localhost:8080/capstone => Ambil semua data
router.get('/capstone/all', capstone.getAll);
// GET localhost:8080/capstone => Ambil data parking_area
router.get('/capstone/parking_area', capstone.getParkingArea);
// GET localhost:8080/capstone => Ambil data hoops_entity
router.get('/capstone/hoops_entity', capstone.getHoopsEntity);

// GET localhost:8080/capstone/? => Ambil data parking_area berdasarkan id = ?
router.get('/capstone/parking_area/:id', capstone.getParkingAreaID);
// GET localhost:8080/capstone/? => Ambil data hoops_entity berdasarkan id = ?
router.get('/capstone/hoops_entity/:id', capstone.getHoopsEntityID);

// POST localhost:8080/capstone/add => Tambah data parking_area
router.post('/capstone/parking_area/add', capstone.addParkingArea);
// POST localhost:8080/capstone/add => Tambah data hoops_entity
router.post('/capstone/hoops_entity/add', capstone.addHoopsEntity);

// POST localhost:8080/capstone/edit/? => Edit data parking_area
router.post('/capstone/parking_area/edit/:id', capstone.editParkingArea);
// POST localhost:8080/capstone/edit/? => Edit data hoops_entity
router.post('/capstone/hoops_entity/edit/:id', capstone.editHoopsEntity);

// POST localhost:8080/capstone/delete/? => Delete data parking_detail
router.post('/capstone/parking_area/delete/:id', capstone.deleteParkingAreaID);
// POST localhost:8080/capstone/delete/? => Delete data parking_detail
router.post('/capstone/hoops_entity/delete/:id', capstone.deleteHoopsEntityID);

/*// POST localhost:8080/capstone/delete => Delete all data
router.post('/capstone/delete', capstone.deleteAll);*/

module.exports = router;