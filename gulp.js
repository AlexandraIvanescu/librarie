/**
 * Created by Alexandra Ale on 05.03.2017.
 */
var gulp = require('gulp');
var sass = require('gulp-sass');

var config = {
    bootstrapDir: 'src/main/webapp/components/bootstrap-sass',
    publicDir: 'src/main/webapp'
};

gulp.task('css', function () {
    return gulp.src('src/main/webapp/app.scss')
        .pipe(sass({
            includePaths: [config.bootstrapDir + '/assets/stylesheets']
        }))
        .pipe(gulp.dest(config.publicDir));
});

gulp.task('fonts', function () {
    return gulp.src(config.bootstrapDir + '/assets/fonts/bootstrap/*')
        .pipe(gulp.dest(config.publicDir + '/fonts/bootstrap'));
});

gulp.task('default', ['css', 'fonts']);
