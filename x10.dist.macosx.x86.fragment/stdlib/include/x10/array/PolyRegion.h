#ifndef __X10_ARRAY_POLYREGION_H
#define __X10_ARRAY_POLYREGION_H

#include <x10rt.h>


#define X10_ARRAY_REGION_H_NODEPS
#include <x10/array/Region.h>
#undef X10_ARRAY_REGION_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace array { 
class PolyRow;
} } 
namespace x10 { namespace array { 
class PolyMat;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace array { 
class Point;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
class UnsupportedOperationException;
} } 
namespace x10 { namespace compiler { 
class Incomplete;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace array { 
class PolyScanner;
} } 
namespace x10 { namespace array { 
class PolyMatBuilder;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class ClassCastException;
} } 
namespace x10 { namespace array { 
class RectRegion;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace array { 
class EmptyRegion;
} } 
namespace x10 { namespace compiler { 
class TempNoInline_3;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace io { 
class Printer;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace array { 

class PolyRegion : public x10::array::Region   {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::lang::Iterable<x10aux::ref<x10::array::Point> >::itable<x10::array::PolyRegion > _itable_0;
    
    static x10::lang::Any::itable<x10::array::PolyRegion > _itable_1;
    
    void _instance_init();
    
    x10aux::ref<x10::array::PolyMat> FMGL(mat);
    
    virtual x10_boolean isConvex();
    x10_int FMGL(size);
    
    virtual x10_int size();
    virtual x10_int indexOf(x10aux::ref<x10::array::Point> id__60);
    virtual x10aux::ref<x10::lang::Iterator<x10aux::ref<x10::array::Point> > >
      iterator(
      );
    virtual x10aux::ref<x10::array::Region> intersection(x10aux::ref<x10::array::Region> t);
    virtual x10_boolean contains(x10aux::ref<x10::array::Region> that);
    virtual x10aux::ref<x10::array::Region> projection(x10_int axis);
    virtual x10aux::ref<x10::array::Region> eliminate(x10_int axis);
    virtual x10aux::ref<x10::array::Region> product(x10aux::ref<x10::array::Region> r);
    static void copy(x10aux::ref<x10::array::PolyMatBuilder> tt, x10aux::ref<x10::array::PolyMat> ff,
                     x10_int offset);
    virtual x10aux::ref<x10::array::Region> translate(x10aux::ref<x10::array::Point> v);
    static void translate(x10aux::ref<x10::array::PolyMatBuilder> tt,
                          x10aux::ref<x10::array::PolyMat> ff,
                          x10aux::ref<x10::array::Point> v);
    virtual x10_boolean isEmpty();
    virtual x10aux::ref<x10::array::Region> computeBoundingBox();
    virtual x10_boolean contains(x10aux::ref<x10::array::Point> p);
    static x10_int FMGL(ROW);
    
    static void FMGL(ROW__do_init)();
    static void FMGL(ROW__init)();
    static volatile x10aux::status FMGL(ROW__status);
    static inline x10_int FMGL(ROW__get)() {
        if (FMGL(ROW__status) != x10aux::INITIALIZED) {
            FMGL(ROW__init)();
        }
        return x10::array::PolyRegion::FMGL(ROW);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(ROW__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(ROW__id);
    
    static x10_int FMGL(COL);
    
    static void FMGL(COL__do_init)();
    static void FMGL(COL__init)();
    static volatile x10aux::status FMGL(COL__status);
    static inline x10_int FMGL(COL__get)() {
        if (FMGL(COL__status) != x10aux::INITIALIZED) {
            FMGL(COL__init)();
        }
        return x10::array::PolyRegion::FMGL(COL);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(COL__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(COL__id);
    
    static x10aux::ref<x10::array::Region> makeBanded(x10_int rowMin,
                                                      x10_int colMin,
                                                      x10_int rowMax,
                                                      x10_int colMax,
                                                      x10_int upper,
                                                      x10_int lower);
    static x10aux::ref<x10::array::Region> makeBanded(x10_int size,
                                                      x10_int upper,
                                                      x10_int lower);
    static x10aux::ref<x10::array::Region> makeUpperTriangular2(x10_int rowMin,
                                                                x10_int colMin,
                                                                x10_int size);
    static x10aux::ref<x10::array::Region> makeLowerTriangular2(x10_int rowMin,
                                                                x10_int colMin,
                                                                x10_int size);
    static x10aux::ref<x10::array::Region> make(x10aux::ref<x10::array::PolyMat> pm);
    void _constructor(x10aux::ref<x10::array::PolyMat> pm, x10_boolean hack198);
    
    static x10aux::ref<x10::array::PolyRegion> _make(x10aux::ref<x10::array::PolyMat> pm,
                                                     x10_boolean hack198);
    
    virtual x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_int> >
      min(
      );
    virtual x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_int> >
      max(
      );
    virtual void printInfo(x10aux::ref<x10::io::Printer> out);
    virtual x10aux::ref<x10::lang::String> toString();
    virtual x10aux::ref<x10::array::PolyRegion> x10__array__PolyRegion____x10__array__PolyRegion__this(
      );
    void __fieldInitializers1563();
    public: virtual x10_int indexOf(x10_int p0);
    public: virtual x10_int indexOf(x10_int p0, x10_int p1);
    public: virtual x10_int indexOf(x10_int p0, x10_int p1, x10_int p2);
    public: virtual x10_int indexOf(x10_int p0, x10_int p1, x10_int p2, x10_int p3);
    public: virtual x10_boolean contains(x10_int p0);
    public: virtual x10_boolean contains(x10_int p0, x10_int p1);
    public: virtual x10_boolean contains(x10_int p0, x10_int p1, x10_int p2);
    public: virtual x10_boolean contains(x10_int p0, x10_int p1, x10_int p2, x10_int p3);
    public: virtual x10_int min(x10_int p0);
    public: virtual x10_int max(x10_int p0);
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_ARRAY_POLYREGION_H

namespace x10 { namespace array { 
class PolyRegion;
} } 

#ifndef X10_ARRAY_POLYREGION_H_NODEPS
#define X10_ARRAY_POLYREGION_H_NODEPS
#include <x10/array/Region.h>
#include <x10/lang/Int.h>
#include <x10/array/PolyRow.h>
#include <x10/array/PolyMat.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Iterator.h>
#include <x10/array/Point.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/UnsupportedOperationException.h>
#include <x10/compiler/Incomplete.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Iterator.h>
#include <x10/array/PolyScanner.h>
#include <x10/array/PolyMatBuilder.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/ClassCastException.h>
#include <x10/array/RectRegion.h>
#include <x10/lang/String.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Iterator.h>
#include <x10/array/Array.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Iterator.h>
#include <x10/array/EmptyRegion.h>
#include <x10/compiler/TempNoInline_3.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/io/Printer.h>
#include <x10/lang/Iterator.h>
#ifndef X10_ARRAY_POLYREGION_H_GENERICS
#define X10_ARRAY_POLYREGION_H_GENERICS
template<class __T> x10aux::ref<__T> x10::array::PolyRegion::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::PolyRegion> this_ = new (memset(x10aux::alloc<x10::array::PolyRegion>(), 0, sizeof(x10::array::PolyRegion))) x10::array::PolyRegion();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_POLYREGION_H_GENERICS
#endif // __X10_ARRAY_POLYREGION_H_NODEPS
