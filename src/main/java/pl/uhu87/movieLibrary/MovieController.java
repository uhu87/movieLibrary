package pl.uhu87.movieLibrary;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("")
    public List<Movie> getAllMovies(){
        return movieRepository.getAll();
    }

    @GetMapping("/{id}")
    public Movie getById (@PathVariable("id") int id){
        return movieRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Movie> movies){
       return movieRepository.save(movies);
    }

    @PutMapping("/{id}")
    public int update (@PathVariable("id") int id, @RequestBody Movie movieToUpdate){

        Movie movie = movieRepository.getById(id);
        if(movie != null){
            movie.setTitle(movieToUpdate.getTitle());
            movie.setRating(movieToUpdate.getRating());
            movieRepository.update(movie);
            return 1;
        } else {
            return -1;
        }
    }

    @PatchMapping("/{id}")
    public int partialUpdate(@PathVariable("id") int id, @RequestBody Movie movieToUpdate){
        Movie movie = movieRepository.getById(id);
        if(movie != null){
            if(movieToUpdate.getTitle()!=null){movie.setTitle(movieToUpdate.getTitle());}
            if(movieToUpdate.getRating()>0){movie.setRating(movieToUpdate.getRating());}
            movieRepository.update(movie);
            return 1;
        } else {

            return -1;
        }
    }

    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") int id){
        return movieRepository.delete(id);
    }
}
