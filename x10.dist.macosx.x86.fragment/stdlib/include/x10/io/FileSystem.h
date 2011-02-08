#ifndef __X10_IO_FILESYSTEM_H
#define __X10_IO_FILESYSTEM_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace lang { 
class Char;
} } 
#include <x10/lang/Char.struct_h>
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace io { 
class File;
} } 
namespace x10 { namespace lang { 
class UnsupportedOperationException;
} } 
namespace x10 { namespace compiler { 
class Incomplete;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class Long;
} } 
#include <x10/lang/Long.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace io { 

class FileSystem : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    static x10_char FMGL(SEPARATOR_CHAR);
    
    static void FMGL(SEPARATOR_CHAR__do_init)();
    static void FMGL(SEPARATOR_CHAR__init)();
    static volatile x10aux::status FMGL(SEPARATOR_CHAR__status);
    static inline x10_char FMGL(SEPARATOR_CHAR__get)() {
        if (FMGL(SEPARATOR_CHAR__status) != x10aux::INITIALIZED) {
            FMGL(SEPARATOR_CHAR__init)();
        }
        return x10::io::FileSystem::FMGL(SEPARATOR_CHAR);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(SEPARATOR_CHAR__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(SEPARATOR_CHAR__id);
    
    static x10aux::ref<x10::lang::String> FMGL(SEPARATOR);
    
    static void FMGL(SEPARATOR__do_init)();
    static void FMGL(SEPARATOR__init)();
    static volatile x10aux::status FMGL(SEPARATOR__status);
    static inline x10aux::ref<x10::lang::String> FMGL(SEPARATOR__get)() {
        if (FMGL(SEPARATOR__status) != x10aux::INITIALIZED) {
            FMGL(SEPARATOR__init)();
        }
        return x10::io::FileSystem::FMGL(SEPARATOR);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(SEPARATOR__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(SEPARATOR__id);
    
    static x10_char FMGL(PATH_SEPARATOR_CHAR);
    
    static void FMGL(PATH_SEPARATOR_CHAR__do_init)();
    static void FMGL(PATH_SEPARATOR_CHAR__init)();
    static volatile x10aux::status FMGL(PATH_SEPARATOR_CHAR__status);
    static inline x10_char FMGL(PATH_SEPARATOR_CHAR__get)() {
        if (FMGL(PATH_SEPARATOR_CHAR__status) != x10aux::INITIALIZED) {
            FMGL(PATH_SEPARATOR_CHAR__init)();
        }
        return x10::io::FileSystem::FMGL(PATH_SEPARATOR_CHAR);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(PATH_SEPARATOR_CHAR__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(PATH_SEPARATOR_CHAR__id);
    
    static x10aux::ref<x10::lang::String> FMGL(PATH_SEPARATOR);
    
    static void FMGL(PATH_SEPARATOR__do_init)();
    static void FMGL(PATH_SEPARATOR__init)();
    static volatile x10aux::status FMGL(PATH_SEPARATOR__status);
    static inline x10aux::ref<x10::lang::String> FMGL(PATH_SEPARATOR__get)() {
        if (FMGL(PATH_SEPARATOR__status) != x10aux::INITIALIZED) {
            FMGL(PATH_SEPARATOR__init)();
        }
        return x10::io::FileSystem::FMGL(PATH_SEPARATOR);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(PATH_SEPARATOR__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(PATH_SEPARATOR__id);
    
    virtual void _kwd__delete(x10aux::ref<x10::io::File> id__99);
    virtual void deleteOnExit(x10aux::ref<x10::io::File> id__100);
    virtual void rename(x10aux::ref<x10::io::File> f, x10aux::ref<x10::io::File> t);
    virtual void mkdir(x10aux::ref<x10::io::File> id__101);
    virtual void mkdirs(x10aux::ref<x10::io::File> id__102);
    virtual x10_boolean exists(x10aux::ref<x10::io::File> id__103);
    virtual x10_long size(x10aux::ref<x10::io::File> id__104);
    virtual x10aux::ref<x10::lang::Rail<x10aux::ref<x10::io::File> > > listFiles(
      x10aux::ref<x10::io::File> id__105);
    virtual x10aux::ref<x10::lang::Rail<x10aux::ref<x10::io::File> > >
      listFiles(
      x10aux::ref<x10::io::File> id__106,
      x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::io::File>, x10_boolean> > id__108);
    virtual x10aux::ref<x10::io::FileSystem> x10__io__FileSystem____x10__io__FileSystem__this(
      );
    void _constructor();
    
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_IO_FILESYSTEM_H

namespace x10 { namespace io { 
class FileSystem;
} } 

#ifndef X10_IO_FILESYSTEM_H_NODEPS
#define X10_IO_FILESYSTEM_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Char.h>
#include <x10/lang/String.h>
#include <x10/io/File.h>
#include <x10/lang/UnsupportedOperationException.h>
#include <x10/compiler/Incomplete.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Long.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Fun_0_1.h>
#ifndef X10_IO_FILESYSTEM_H_GENERICS
#define X10_IO_FILESYSTEM_H_GENERICS
#endif // X10_IO_FILESYSTEM_H_GENERICS
#endif // __X10_IO_FILESYSTEM_H_NODEPS
