var map;

      function initMap1() {
        map = new mappls.Map("map", {
          center: [17.0704, 74.5352], // Initial map center at Vita
          zoomControl: true,
          location: true,
          zoom: 12
        });

        map.addListener("load", function () {
          var geoData = {
            type: "FeatureCollection",
            features: [
              {
                type: "Feature",
                properties: {
                  htmlPopup: "Vita"
                },
                geometry: {
                  type: "Point",
                  coordinates: [17.273923242572124, 74.536408043929]
                }
              },
              {
                type: "Feature",
                properties: {
                  htmlPopup: "Nevari"
                },
                geometry: {
                  type: "Point",
                  coordinates: [17.306921718346675, 74.48178542752923]
                }
              },
              {
                type: "Feature",
                properties: {
                  htmlPopup: "Kadegav"
                },
                geometry: {
                  type: "Point",
                  coordinates: [17.29644203301885, 74.3338494110774]
                }
              },
              {
                type: "Feature",
                properties: {
                  htmlPopup: "AIT"
                },
                geometry: {
                  type: "Point",
                  coordinates: [17.225693647287176, 74.53942635813947]
                }
              },
              {
                type: "Feature",
                properties: {
                  htmlPopup: "Khanapur"
                },
                geometry: {
                  type: "Point",
                  coordinates: [17.2628344312658, 74.7230386162223]
                }
              }
            ]
          };

          var marker = mappls.Marker({
        map: map,
        position: geoData,
        icon_url: "https://i.ibb.co/LhpyVy4/bus-1.png",
        fitbounds: true,
        clusters: true,
        clustersIcon: "https://mappls.com/images/2.png",
        fitboundOptions: {
          padding: 120,
          duration: 1000,
        },
        popupOptions: {
          offset: { bottom: [0, -20] },
        },
      });
        });
      }