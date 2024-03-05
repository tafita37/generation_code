<script setup>
  import { RouterLink, RouterView } from 'vue-router';
</script>

<template>
  <div class="sup">
    <ul class="header">
      <li><RouterLink to="/allMagasinUser">Liste de MagasinUser</RouterLink></li><li><RouterLink to="/allTypeRam">Liste de TypeRam</RouterLink></li><li><RouterLink to="/allRam">Liste de Ram</RouterLink></li><li><RouterLink to="/allTypeDisque">Liste de TypeDisque</RouterLink></li><li><RouterLink to="/allDisque">Liste de Disque</RouterLink></li><li><RouterLink to="/allMarque">Liste de Marque</RouterLink></li><li><RouterLink to="/allLaptop">Liste de Laptop</RouterLink></li><li><RouterLink to="/allProcesseur">Liste de Processeur</RouterLink></li><li><RouterLink to="/allStockLaptopMagasin">Liste de StockLaptopMagasin</RouterLink></li><li><RouterLink to="/allPointVente">Liste de PointVente</RouterLink></li><li><RouterLink to="/allTransfertLaptop">Liste de TransfertLaptop</RouterLink></li><li><RouterLink to="/allStockPointVente">Liste de StockPointVente</RouterLink></li>
      <li><RouterLink to="/newMagasinUser">Insertion de magasinUser</RouterLink></li><li><RouterLink to="/newTypeRam">Insertion de typeRam</RouterLink></li><li><RouterLink to="/newRam">Insertion de ram</RouterLink></li><li><RouterLink to="/newTypeDisque">Insertion de typeDisque</RouterLink></li><li><RouterLink to="/newDisque">Insertion de disque</RouterLink></li><li><RouterLink to="/newMarque">Insertion de marque</RouterLink></li><li><RouterLink to="/newLaptop">Insertion de laptop</RouterLink></li><li><RouterLink to="/newProcesseur">Insertion de processeur</RouterLink></li><li><RouterLink to="/newStockLaptopMagasin">Insertion de stockLaptopMagasin</RouterLink></li><li><RouterLink to="/newPointVente">Insertion de pointVente</RouterLink></li><li><RouterLink to="/newTransfertLaptop">Insertion de transfertLaptop</RouterLink></li><li><RouterLink to="/newStockPointVente">Insertion de stockPointVente</RouterLink></li>
    </ul>
  </div>
  <RouterView />
</template>

<style scoped>
  .header_info {
    background-color: #120851;
    width: 100%;
    margin: 0;
    color: white;
    line-height: 2.8;
    display: flex;
    align-items: center;
  }

  .header_info li {
    margin-left: 30px;
  }

  .sup {
    background-color: #120851;
  }

  .header {
    color: white;
    margin: 1px;
    width: 60%;
    top: 40px;
    display: flex;
    line-height: 3.8;
    align-items: center;
    justify-content: space-between;
  }

  ul {
    width: 100%;
  }

  li {
    list-style: none;
  }
</style>
