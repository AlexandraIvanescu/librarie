/**
 * Created by Alexandra Ale on 05/06/2017.
 */

angular.module('libraryApp')
    .config(function ($mdThemingProvider) {
        $mdThemingProvider.definePalette('amazingPaletteName', {
            '50': '#eedadd',
            '100': '#eebcc1',
            '200': '#de8989',
            '300': '#d46262',
            '400': '#de423f',
            '500': '#d82601',
            '600': '#d52824',
            '700': '#c21e1e',
            '800': '#b51717',
            '900': '#a60b0b',
            'A100': '#ee796f',
            'A200': '#ee4141',
            'A400': '#ee0633',
            'A700': '#c30000',
            'contrastDefaultColor': 'light',
            'contrastDarkColors': ['50', '100', '200', '300', '400', 'A100'],
            'contrastLightColors': undefined
        });

        $mdThemingProvider.theme('default')
            .primaryPalette('amazingPaletteName')
    });
